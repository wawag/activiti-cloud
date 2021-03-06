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
package org.activiti.cloud.services.query.model;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import org.activiti.cloud.api.process.model.CloudBPMNActivity;

@Entity(name="BPMNActivity")
@Table(name="BPMN_ACTIVITY", indexes={
    @Index(name="bpmn_activity_status_idx", columnList="status", unique=false),
    @Index(name="bpmn_activity_processInstance_idx", columnList="processInstanceId", unique=false),
    @Index(name="bpmn_activity_processInstance_elementId_idx", columnList="processInstanceId,elementId,executionId", unique=true)
})
public class BPMNActivityEntity extends BaseBPMNActivityEntity implements CloudBPMNActivity {

    public BPMNActivityEntity() { }

    public BPMNActivityEntity(String serviceName,
                              String serviceFullName,
                              String serviceVersion,
                              String appName,
                              String appVersion) {
        super(serviceName,
              serviceFullName,
              serviceVersion,
              appName,
              appVersion);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BPMNActivityEntity [toString()=").append(super.toString()).append("]");
        return builder.toString();
    }

}