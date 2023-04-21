package com.csgo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class InventoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gun_id")
    private int gunId;
    @Column(name = "player_id")
    private int playerId;
    @Column(name = "gun_name")
    private String gunName;
    @Column(name = "gun_status")
    private String gunStatus;
    @Column(name = "gun_ST")
    private boolean isST;
    @Column(name = "gun_price")
    private double gunPrice;

}
