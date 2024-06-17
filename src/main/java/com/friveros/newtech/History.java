package com.friveros.newtech;

import com.friveros.newtech.dto.Point;
import com.friveros.newtech.enums.ActionEnums;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.common.constraint.NotNull;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@MongoEntity(collection = "history")
@Tag( description = "history")
@Schema(description = "history Schema for Pages")
public class History extends ReactivePanacheMongoEntity {
    public History(ObjectId pageId, Instant changedAt, String changedBy, String changedIdBy, List<ActionEnums> actions, Point point) {
        this.pageId = pageId;
        this.changedAt = changedAt;
        this.changedBy = changedBy;
        this.changedIdBy = changedIdBy;
        this.actions = actions;
        this.point = point;
    }

    @NotNull
    public ObjectId pageId;
    @NotNull
    public Long numberId;
    @NotNull
    public Instant changedAt;
    @NotNull
    public String changedBy;
    @NotNull
    public String changedIdBy;
    @NotNull
    public List<ActionEnums> actions;
    @NotNull
    public Point point;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        History history = (History) o;
        return this.id == history.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
    @Override
    public String toString() {
        return "History {" +
                "pageId='" + pageId + '\'' +
                ", changedAt=" + changedAt +
                ", changedIdBy='" + changedIdBy + '\'' +
                ", changedBy='" + changedBy + '\'' +
                ", actions='" + actions.toString() + '\'' +
                ", point='" + point.toString() + '\'' +
                ", id=" + id +
                '}';
    }
}
