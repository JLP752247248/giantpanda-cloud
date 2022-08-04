package com.panda.fileserver.filemanager.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="tb_file_info")
@Data
public class FileInfoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="id")
    private Long id;

    @Column(name="file_uuid", nullable = false)
    private String fileUuid;

    @Column(name="file_name", nullable = false)
    private String fileName;

    @Column(name="directory")
    private String directory;

    @Column(name="file_type", nullable = false)
    private int fileType;

    @Column(name="md5", nullable = false)
    private String md5;
}
