package com.softserve.teamproject.repository.custom;

import com.softserve.teamproject.entity.ScheduledTask;
import java.util.List;

public interface ScheduledTaskRepositoryCustom {
  List<ScheduledTask> getActualTasks();
  List<ScheduledTask> getOldTasks();
}
