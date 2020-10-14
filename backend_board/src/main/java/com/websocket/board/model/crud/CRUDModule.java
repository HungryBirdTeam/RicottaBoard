package com.websocket.board.model.crud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CRUDModule implements Serializable {
    @Enumerated(EnumType.STRING)
    private CRUDType crudType;
    @Enumerated(EnumType.STRING)
    private ModuleType moduleType;
    private Object moduleObject;
}
