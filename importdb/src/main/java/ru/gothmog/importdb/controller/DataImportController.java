package ru.gothmog.importdb.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

@Controller
public class DataImportController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("importDataJob")
    private Job importDataJob;

    @GetMapping
    @RequestMapping(value = "/import/file")
    public String uploadFile() {
        return "uploadFile";
    }
    @RequestMapping(value="/import/file/uploadToDB", method=RequestMethod.POST)
    public String create(@RequestParam("file") MultipartFile multipartFile) throws IOException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        //Save multipartFile file in a temporary physical folder
        String path = new ClassPathResource("tmpuploads/").getURL().getPath();//it's assumed you have a folder called tmpuploads in the resources folder
        File fileToImport = new File(path + multipartFile.getOriginalFilename());

        //Launch the Batch Job
        JobExecution jobExecution = jobLauncher.run(importDataJob, new JobParametersBuilder()
                .addString("fullPathFileName", fileToImport.getAbsolutePath())
                .toJobParameters());
        return "OK";
    }


}
