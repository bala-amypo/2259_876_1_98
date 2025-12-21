public interface ProgressService {

    Progress recordProgress(Long lessonId, Progress progress);

    Progress getProgress(Long progressId);

    List<Progress> getAllProgress();
}
