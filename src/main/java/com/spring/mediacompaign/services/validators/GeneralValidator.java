package com.spring.mediacompaign.services.validators;

import com.spring.mediacompaign.dao.models.VersionedModel;
import org.springframework.stereotype.Component;

@Component
public interface GeneralValidator<T extends VersionedModel> {

    default VersionedModel post(T model){
        if (model.getId() != null) {
            VersionedModel versionedModel = new VersionedModel();
            versionedModel.setErrorMessage("for post ID must be null");
            return versionedModel;
        }
        return null;
    }

    default VersionedModel put(T model){
        if (model.getId() == null) {
            VersionedModel versionedModel = new VersionedModel();
            versionedModel.setErrorMessage("for put ID must not be null");
            return versionedModel;
        }
        return null;
    }

}
