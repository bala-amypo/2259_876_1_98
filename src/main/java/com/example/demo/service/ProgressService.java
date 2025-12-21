package com.example.demo.service;

import com.example.demo.model.Progress;

import java.util.List;

public interface ProgressService {

    Progress createProgress(Progress progress);

    Progress updateProgress(Long id, Progress progress);

    Progress getProgress(Long id);

    List<Progress> getAllProgress();
}
