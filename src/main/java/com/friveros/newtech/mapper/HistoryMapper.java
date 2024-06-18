package com.friveros.newtech.mapper;

import com.friveros.newtech.History;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface HistoryMapper {
    /**
     * Maps all fields from {@code fight} to a {@link com.friveros.newtech.schema.History}
     * @param history
     * @return
     */
    com.friveros.newtech.schema.History toSchema(History history);
    @Mapping(target = "id", ignore = true)
    public abstract void updatePage(History dto, @MappingTarget History entity);
    default String toString(ObjectId objectId) {
        return objectId.toString();
    }
}
