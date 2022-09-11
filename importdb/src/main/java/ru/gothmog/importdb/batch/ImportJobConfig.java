package ru.gothmog.importdb.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.FileSystemResource;
import ru.gothmog.importdb.batch.process.AreaInformDataProcessor;
import ru.gothmog.importdb.batch.process.CityInformDataProcessor;
import ru.gothmog.importdb.batch.process.DistrictInformDataProcessor;
import ru.gothmog.importdb.batch.process.RegionInformDataProcessor;
import ru.gothmog.importdb.batch.step.AreaInformDataBatchStep;
import ru.gothmog.importdb.batch.step.CityInformDataBatchStep;
import ru.gothmog.importdb.batch.step.DistrictInformDataBatchStep;
import ru.gothmog.importdb.batch.step.RegionInformDataBatchStep;
import ru.gothmog.importdb.mapper.ImportDataPreparedStatementSetter;
import ru.gothmog.importdb.model.inform.AreaInformData;
import ru.gothmog.importdb.model.inform.CityInformData;
import ru.gothmog.importdb.model.ImportData;
import ru.gothmog.importdb.model.inform.DistrictInformData;
import ru.gothmog.importdb.model.inform.RegionInformData;

import javax.sql.DataSource;

@Configuration
public class ImportJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("dataSource")
    public DataSource dataSource;
    @Bean
    public ItemProcessor<CityInformData,CityInformData> cityInformDataItemProcessor(){
        return new CityInformDataProcessor();
    }
    @Bean
    public ItemProcessor<RegionInformData,RegionInformData> regionInformDataItemProcessor(){
        return new RegionInformDataProcessor();
    }
    @Bean
    public ItemProcessor<DistrictInformData,DistrictInformData> districtInformDataItemProcessor(){
        return new DistrictInformDataProcessor();
    }
    @Bean
    public ItemProcessor<AreaInformData,AreaInformData> areaInformDataItemProcessor(){
        return new AreaInformDataProcessor();
    }

    @Bean
    @Scope(value = "step", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public FlatFileItemReader<ImportData> importDataReader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile){
        FlatFileItemReader<ImportData> reader = new FlatFileItemReader<>();

        reader.setResource(new FileSystemResource("D:\\csv_import\\import_data_2018_3.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<ImportData>() {{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"idLocality","idKeyInformName","valueAll","reportingPeriod"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ImportData>(){{
                setTargetType(ImportData.class);
            }});
        }});
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<ImportData> writer(){
        String sql = "insert into renov.import_data(id_locality, id_key_inform_name, value_all, reporting_period) VALUES (?,?,?,?)";
        JdbcBatchItemWriter<ImportData> writerImportData = new JdbcBatchItemWriter<>();
        writerImportData.setDataSource(dataSource);
        writerImportData.setSql(sql);
        ItemPreparedStatementSetter<ImportData> statementSetter = new ImportDataPreparedStatementSetter();
        writerImportData.setItemPreparedStatementSetter(statementSetter);
        return writerImportData;
    }
    @Bean(name = "importDataJob")
    @Primary
    public Job importDataJob(ItemReader<ImportData> importDataReader){
        return jobBuilderFactory.get("importDataJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepImportData(importDataReader))
                .end()
                .build();
    }
    @Bean
    public Step stepImportData(ItemReader<ImportData> importDataReader){
        return stepBuilderFactory.get("stepImportData").<ImportData,ImportData>chunk(100)
                .reader(importDataReader)
                .writer(writer())
                .build();
    }
    @Bean(name = "job-cityInfoData")
    public Job jobCityInformData(JobBuilderFactory jbc, StepBuilderFactory sbf,CityInformDataBatchStep cityInformDataBatchStep){
        Step cityInformDataStep = sbf.get("db reader cityinfodata").<CityInformData,CityInformData>chunk(100)
                .reader(cityInformDataBatchStep.tableCityReader(dataSource))
                .processor(cityInformDataItemProcessor())
                .writer(cityInformDataBatchStep.jdbcBatchItemWriterCityInform(dataSource))
                .build();
        return jbc.get("data import city inform data")
                .incrementer(new RunIdIncrementer())
                .start(cityInformDataStep)
                .build();
    }
    @Bean(name = "job-regionInfoData")
    public Job jobRegionInformData(JobBuilderFactory jbc, StepBuilderFactory sbf, RegionInformDataBatchStep regionInformDataBatchStep){
        Step regionInformDataStep = sbf.get("db reader regioninfodata").<RegionInformData,RegionInformData>chunk(100)
                .reader(regionInformDataBatchStep.tableRegionReader(dataSource))
                .processor(regionInformDataItemProcessor())
                .writer(regionInformDataBatchStep.jdbcBatchItemWriterRegionInform(dataSource))
                .build();
        return jbc.get("data import region inform data")
                .incrementer(new RunIdIncrementer())
                .start(regionInformDataStep)
                .build();
    }
    @Bean(name = "job-districtInfoData")
    public Job jobDistrictInformData(JobBuilderFactory jbc, StepBuilderFactory sbf, DistrictInformDataBatchStep districtInformDataBatchStep){
        Step districtInformDataStep = sbf.get("db reader districtInformData").<DistrictInformData,DistrictInformData>chunk(100)
                .reader(districtInformDataBatchStep.tableRegionDistrictReader(dataSource))
                .processor(districtInformDataItemProcessor())
                .writer(districtInformDataBatchStep.jdbcBatchItemWriterDistrictInform(dataSource))
                .build();
        return jbc.get("data import district inform data")
                .incrementer(new RunIdIncrementer())
                .start(districtInformDataStep)
                .build();
    }
    @Bean(name = "job-areaInfoData")
    public Job jobAreaInformData(JobBuilderFactory jbc, StepBuilderFactory sbf, AreaInformDataBatchStep areaInformDataBatchStep){
        Step areaInformDataStep = sbf.get("db reader areaInformData").<AreaInformData,AreaInformData>chunk(100)
                .reader(areaInformDataBatchStep.tableRegionAreaReader(dataSource))
                .processor(areaInformDataItemProcessor())
                .writer(areaInformDataBatchStep.jdbcBatchItemWriterAreaInform(dataSource))
                .build();
        return jbc.get("data import area inform data ")
                .incrementer(new RunIdIncrementer())
                .start(areaInformDataStep)
                .build();
    }

}
