package com.virtusa.querymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.querymanagement.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
