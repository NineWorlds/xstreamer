package us.nineworlds.xstreamer.eventbus;

public class EventBus {

	private EventDispatcher eventdispatcher;
	private static EventBus eventBus;
	
	private EventBus() {
		eventdispatcher = new EventDispatcher();
	}
	
	public static EventBus getInstance() {
		if (eventBus == null) {
			eventBus = new EventBus();
		}
		return eventBus;
	}
	
	public void register(Object eventSubscriber) {
		eventdispatcher.addHandler(eventSubscriber);
	}
	
	public void post(Object event) {
		eventdispatcher.publish(event);
	}
		
}
