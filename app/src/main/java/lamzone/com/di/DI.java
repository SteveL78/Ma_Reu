package lamzone.com.di;

import lamzone.com.service.DummyMeetingApiService;
import lamzone.com.service.MeetingApiService;

/**
 * Dependency injector to get instance of services
 * Created by Steve LEROY on 04/04/2020.
 */
public class DI {

    private static MeetingApiService serviceMeeting = new DummyMeetingApiService();


    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService() {return serviceMeeting;}


    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}