/*
 * Copyright 2017 Alfresco, Inc. and/or its affiliates.
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

package org.activiti.cloud.services.events.converter;

import org.activiti.cloud.services.api.events.ProcessEngineEvent;
import org.activiti.cloud.services.events.VariableCreatedEventImpl;
import org.activiti.cloud.services.events.configuration.RuntimeBundleProperties;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiVariableEventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.activiti.engine.delegate.event.ActivitiEventType.VARIABLE_CREATED;

@Component
public class VariableCreatedEventConverter extends AbstractEventConverter {

    @Autowired
    public VariableCreatedEventConverter(RuntimeBundleProperties runtimeBundleProperties) {
        super(runtimeBundleProperties);
    }

    @Override
    public ProcessEngineEvent from(ActivitiEvent event) {
        ActivitiVariableEventImpl variableEvent = (ActivitiVariableEventImpl) event;

        return new VariableCreatedEventImpl(getApplicationName(),
                                            event.getExecutionId(),
                                            event.getProcessDefinitionId(),
                                            event.getProcessInstanceId(),
                                            variableEvent.getVariableName(),
                                            (variableEvent.getVariableValue() != null) ? String.valueOf(variableEvent.getVariableValue()) : "",
                                            (variableEvent.getVariableType() != null) ? variableEvent.getVariableType().getTypeName() : "",
                                            variableEvent.getTaskId());
    }

    @Override
    public ActivitiEventType handledType() {
        return VARIABLE_CREATED;
    }
}
