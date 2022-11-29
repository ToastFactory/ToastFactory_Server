package com.konkuk.toastfactory.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BasicEntity {

    @Column(updatable = false, name = "create_at") //createdAt은 최초에 생성될 떄 초기화 된 이후 업데이트 되면 안되는 값 이므로 -> 업데이트를 막아둠
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    /** em.persist() 가 수행되기 전 */
    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createAt = now;
        updateAt = now;
        status = Status.ACTIVE;
    }

    /** dirty checking 으로 update 쿼리가 나가기 전 */
    @PreUpdate
    public void postUpdate(){
        updateAt = LocalDateTime.now();
    }
}
