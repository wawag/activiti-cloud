/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.services.query.events.handlers;

import java.util.Date;
import java.util.Optional;

import org.activiti.cloud.services.query.app.repository.TaskRepository;
import org.activiti.cloud.services.query.model.TaskEntity;
import org.activiti.engine.ActivitiException;
import org.activiti.runtime.api.event.CloudRuntimeEvent;
import org.activiti.runtime.api.event.CloudTaskCompletedEvent;
import org.activiti.runtime.api.event.TaskRuntimeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskCompletedEventHandler implements QueryEventHandler {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskCompletedEventHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void handle(CloudRuntimeEvent<?, ?> event) {
        CloudTaskCompletedEvent taskCompletedEvent = (CloudTaskCompletedEvent) event;
        org.activiti.runtime.api.model.Task eventTask = taskCompletedEvent.getEntity();
        Optional<TaskEntity> findResult = taskRepository.findById(eventTask.getId());
        TaskEntity queryTaskEntity = findResult.orElseThrow(
                () -> new ActivitiException("Unable to find task with id: " + eventTask.getId())
        );

        queryTaskEntity.setStatus(eventTask.getStatus());
        queryTaskEntity.setLastModified(new Date(taskCompletedEvent.getTimestamp()));
        taskRepository.save(queryTaskEntity);
    }

    @Override
    public String getHandledEvent() {
        return TaskRuntimeEvent.TaskEvents.TASK_COMPLETED.name();
    }
}
