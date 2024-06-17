package com.friveros.newtech;

import com.friveros.newtech.dto.Point;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Size;

public class Location extends ReactivePanacheMongoEntity {
    @NotNull
    public String name;
    @NotNull
    public Point point;
    @NotNull @Size(max = 255)
    public String address;
    @NotNull
    public Boolean defaultAddress;
}
