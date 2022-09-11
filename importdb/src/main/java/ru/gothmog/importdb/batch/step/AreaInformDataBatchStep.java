package ru.gothmog.importdb.batch.step;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.gothmog.importdb.mapper.AreaInformDataPreparedStatementSetter;
import ru.gothmog.importdb.mapper.AreaInformDataRowMapper;
import ru.gothmog.importdb.model.inform.AreaInformData;

import javax.sql.DataSource;

@Configuration
public class AreaInformDataBatchStep {

    @Autowired
    JobRepository jobRepository;

    public ItemReader tableRegionAreaReader(DataSource dataSource){
        String sql = "select id_locality,id_key_inform_name,value_all,reporting_period from renov.import_data\n" +
                "where\n" +
                "  id_locality = '01010100' or\n" +
                "  id_locality = '01010200' or\n" +
                "  id_locality = '01010300' or\n" +
                "  id_locality = '01010400' or\n" +
                "  id_locality = '01010500' or\n" +
                "  id_locality = '01010600' or\n" +
                "  id_locality = '01010700' or\n" +
                "  id_locality = '01010800' or\n" +
                "  id_locality = '01010900' or\n" +
                "  id_locality = '01011000' or\n" +
                "  id_locality = '01020100' or\n" +
                "  id_locality = '01020200' or\n" +
                "  id_locality = '01020300' or\n" +
                "  id_locality = '01020400' or\n" +
                "  id_locality = '01020500' or\n" +
                "  id_locality = '01020600' or\n" +
                "  id_locality = '01020700' or\n" +
                "  id_locality = '01020800' or\n" +
                "  id_locality = '01020900' or\n" +
                "  id_locality = '01021000' or\n" +
                "  id_locality = '01021100' or\n" +
                "  id_locality = '01021200' or\n" +
                "  id_locality = '01021300' or\n" +
                "  id_locality = '01021400' or\n" +
                "  id_locality = '01021500' or\n" +
                "  id_locality = '01021600' or\n" +
                "  id_locality = '01030100' or\n" +
                "  id_locality = '01030200' or\n" +
                "  id_locality = '01030300' or\n" +
                "  id_locality = '01030400' or\n" +
                "  id_locality = '01030500' or\n" +
                "  id_locality = '01030600' or\n" +
                "  id_locality = '01030700' or\n" +
                "  id_locality = '01030800' or\n" +
                "  id_locality = '01030900' or\n" +
                "  id_locality = '01031000' or\n" +
                "  id_locality = '01031100' or\n" +
                "  id_locality = '01031200' or\n" +
                "  id_locality = '01031300' or\n" +
                "  id_locality = '01031400' or\n" +
                "  id_locality = '01031500' or\n" +
                "  id_locality = '01031600' or\n" +
                "  id_locality = '01031700' or\n" +
                "  id_locality = '01040100' or\n" +
                "  id_locality = '01040200' or\n" +
                "  id_locality = '01040300' or\n" +
                "  id_locality = '01040400' or\n" +
                "  id_locality = '01040500' or\n" +
                "  id_locality = '01040600' or\n" +
                "  id_locality = '01040700' or\n" +
                "  id_locality = '01040800' or\n" +
                "  id_locality = '01040900' or\n" +
                "  id_locality = '01041000' or\n" +
                "  id_locality = '01041100' or\n" +
                "  id_locality = '01041200' or\n" +
                "  id_locality = '01041300' or\n" +
                "  id_locality = '01041400' or\n" +
                "  id_locality = '01041500' or\n" +
                "  id_locality = '01041600' or\n" +
                "  id_locality = '01050100' or\n" +
                "  id_locality = '01050200' or\n" +
                "  id_locality = '01050300' or\n" +
                "  id_locality = '01050400' or\n" +
                "  id_locality = '01050500' or\n" +
                "  id_locality = '01050600' or\n" +
                "  id_locality = '01050700' or\n" +
                "  id_locality = '01050800' or\n" +
                "  id_locality = '01050900' or\n" +
                "  id_locality = '01051000' or\n" +
                "  id_locality = '01051100' or\n" +
                "  id_locality = '01051200' or\n" +
                "  id_locality = '01060100' or\n" +
                "  id_locality = '01060200' or\n" +
                "  id_locality = '01060300' or\n" +
                "  id_locality = '01060400' or\n" +
                "  id_locality = '01060500' or\n" +
                "  id_locality = '01060600' or\n" +
                "  id_locality = '01060700' or\n" +
                "  id_locality = '01060800' or\n" +
                "  id_locality = '01060900' or\n" +
                "  id_locality = '01061000' or\n" +
                "  id_locality = '01061100' or\n" +
                "  id_locality = '01061200' or\n" +
                "  id_locality = '01061300' or\n" +
                "  id_locality = '01061400' or\n" +
                "  id_locality = '01061500' or\n" +
                "  id_locality = '01061600' or\n" +
                "  id_locality = '01070100' or\n" +
                "  id_locality = '01070200' or\n" +
                "  id_locality = '01070300' or\n" +
                "  id_locality = '01070400' or\n" +
                "  id_locality = '01070500' or\n" +
                "  id_locality = '01070600' or\n" +
                "  id_locality = '01070700' or\n" +
                "  id_locality = '01070800' or\n" +
                "  id_locality = '01070900' or\n" +
                "  id_locality = '01071000' or\n" +
                "  id_locality = '01071100' or\n" +
                "  id_locality = '01071200' or\n" +
                "  id_locality = '01080100' or\n" +
                "  id_locality = '01080200' or\n" +
                "  id_locality = '01080300' or\n" +
                "  id_locality = '01080400' or\n" +
                "  id_locality = '01080500' or\n" +
                "  id_locality = '01080600' or\n" +
                "  id_locality = '01080700' or\n" +
                "  id_locality = '01080800' or\n" +
                "  id_locality = '01080900' or\n" +
                "  id_locality = '01081000' or\n" +
                "  id_locality = '01081100' or\n" +
                "  id_locality = '01081200' or\n" +
                "  id_locality = '01081300' or\n" +
                "  id_locality = '01090100' or\n" +
                "  id_locality = '01090200' or\n" +
                "  id_locality = '01090300' or\n" +
                "  id_locality = '01090400' or\n" +
                "  id_locality = '01090500' or\n" +
                "  id_locality = '01090600' or\n" +
                "  id_locality = '01090700' or\n" +
                "  id_locality = '01090800' or\n" +
                "  id_locality = '01100100' or\n" +
                "  id_locality = '01100200' or\n" +
                "  id_locality = '01100300' or\n" +
                "  id_locality = '01100400' or\n" +
                "  id_locality = '01100500' or\n" +
                "  id_locality = '02010100' or\n" +
                "  id_locality = '02010200' or\n" +
                "  id_locality = '02010300' or\n" +
                "  id_locality = '02010400' or\n" +
                "  id_locality = '02010500' or\n" +
                "  id_locality = '02010600' or\n" +
                "  id_locality = '02010700' or\n" +
                "  id_locality = '02010800' or\n" +
                "  id_locality = '02010900' or\n" +
                "  id_locality = '02011000' or\n" +
                "  id_locality = '02011100' or\n" +
                "  id_locality = '02020100' or\n" +
                "  id_locality = '02020200' or\n" +
                "  id_locality = '02020300' or\n" +
                "  id_locality = '02020400' or\n" +
                "  id_locality = '02020500' or\n" +
                "  id_locality = '02020600' or\n" +
                "  id_locality = '02020700' or\n" +
                "  id_locality = '02020800' or\n" +
                "  id_locality = '02020900' or\n" +
                "  id_locality = '02021000'\n" +
                "order by id_key_inform_name";
        JdbcCursorItemReader<AreaInformData> jdbcCursorItemReader = new JdbcCursorItemReader<>();
        jdbcCursorItemReader.setDataSource(dataSource);
        jdbcCursorItemReader.setSql(sql);
        jdbcCursorItemReader.setVerifyCursorPosition(false);
        jdbcCursorItemReader.setRowMapper(new AreaInformDataRowMapper());
        return jdbcCursorItemReader;
    }

    public JdbcBatchItemWriter<AreaInformData> jdbcBatchItemWriterAreaInform(DataSource dataSource){
        String sql = "insert into renov.area_inform_data(id_locality_area, id_key_inform_name, value_area, reporting_period, dynamics) VALUES (?,?,?,?,?)";
        JdbcBatchItemWriter<AreaInformData> areaInformDataJdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        areaInformDataJdbcBatchItemWriter.setDataSource(dataSource);
        areaInformDataJdbcBatchItemWriter.setSql(sql);
        ItemPreparedStatementSetter<AreaInformData> statementSetter = new AreaInformDataPreparedStatementSetter();
        areaInformDataJdbcBatchItemWriter.setItemPreparedStatementSetter(statementSetter);
        return areaInformDataJdbcBatchItemWriter;
    }
}
