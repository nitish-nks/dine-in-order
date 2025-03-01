package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "staffs")
public class Staff extends User {
}
