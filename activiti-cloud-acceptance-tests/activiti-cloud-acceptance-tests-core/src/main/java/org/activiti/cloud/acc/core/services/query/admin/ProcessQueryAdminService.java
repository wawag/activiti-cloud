/*
 * Copyright 2017-2020 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.cloud.acc.core.services.query.admin;

import org.activiti.cloud.api.process.model.CloudBPMNActivity;
import org.activiti.cloud.api.process.model.CloudIntegrationContext;
import org.activiti.cloud.api.process.model.CloudProcessDefinition;
import org.activiti.cloud.api.process.model.CloudProcessInstance;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ProcessQueryAdminService {

    @RequestLine("GET /admin/v1/process-definitions")
    @Headers("Content-Type: application/json")
    PagedModel<CloudProcessDefinition> getProcessDefinitions();

    @RequestLine("GET /admin/v1/process-instances?sort=startDate,desc&sort=id,desc")
    @Headers("Content-Type: application/json")
    PagedModel<CloudProcessInstance> getProcessInstances();

    @RequestLine("DELETE /admin/v1/process-instances")
    CollectionModel<EntityModel<CloudProcessInstance>> deleteProcessInstances();

    @RequestLine("GET /admin/v1/service-tasks?sort=id,desc")
    @Headers("Content-Type: application/json")
    PagedModel<CloudBPMNActivity> getServiceTasks();

    @RequestLine("GET /admin/v1/service-tasks?{query}")
    @Headers("Content-Type: application/json")
    PagedModel<CloudBPMNActivity> getServiceTasksByQuery(@Param("query") String query);

    @RequestLine("GET /admin/v1/service-tasks/{serviceTaskId}")
    @Headers("Content-Type: application/json")
    CloudBPMNActivity getServiceTaskById(@Param("serviceTaskId") String serviceTaskId);

    @RequestLine("GET /admin/v1/process-instances/{processInstanceId}/service-tasks")
    @Headers("Content-Type: application/json")
    PagedModel<CloudBPMNActivity> getServiceTasks(@Param("processInstanceId") String processInstanceId);

    @RequestLine("GET /admin/v1/process-instances/{processInstanceId}/service-tasks?status={status}")
    @Headers("Content-Type: application/json")
    PagedModel<CloudBPMNActivity> getServiceTasksByStatus(@Param("processInstanceId") String processInstanceId,
                                                          @Param("status") String status);

    @RequestLine("GET /admin/v1/service-tasks/{serviceTaskId}/integration-context")
    @Headers("Content-Type: application/json")
    CloudIntegrationContext getCloudIntegrationContext(@Param("serviceTaskId") String serviceTaskId);
}
