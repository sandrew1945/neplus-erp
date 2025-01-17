package com.neplus.erp.schedule;


import com.neplus.erp.model.TmClientPO;
import com.neplus.erp.service.TaskManagerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Component
@Slf4j
public class DeclarationTask
{

    @Resource
    private TaskManagerService taskManagerService;

    @Scheduled(fixedRate = 60000)
    public void createDeclarationTask()
    {
        log.debug("Start Schedule .... ");
        // get current year and month.
        LocalDate now = LocalDate.now();
        String yearAndMonth = now.format(DateTimeFormatter.ofPattern("LL-u"));
        String quarterOfYear = now.format(DateTimeFormatter.ofPattern("Q"));
        log.debug(yearAndMonth);
        // get all client which didn't create task this month.
        List<TmClientPO> clients = taskManagerService.getNoTaskClientByDate(yearAndMonth);
        // Determine and generate tasks
        taskManagerService.createTaskByDetermination(clients, yearAndMonth, quarterOfYear);
        log.debug("End of Schedule .... ");
    }
}
