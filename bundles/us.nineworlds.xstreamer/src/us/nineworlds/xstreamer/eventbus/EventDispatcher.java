package us.nineworlds.xstreamer.eventbus;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class EventDispatcher {
	
	
	private class HandlerInfo {
		public WeakReference<Object> handler;
		public Method method;
	}
	private Map<Class<?>, Set<HandlerInfo>> handlers = new HashMap<Class<?>, Set<HandlerInfo>>();
	 
	public void addHandler(final Object handler) {
		for (Method method : handler.getClass().getMethods()) {
            if (method.getAnnotation(EventHandler.class)!=null) {
            	Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length == 1)
                {
                    addTypeSpecificHandler(handler, paramTypes[0],method);
                }
            }
        }
	}

	private void addTypeSpecificHandler(final Object handler, final Class<?> type, Method method) {
        Set<HandlerInfo> typeHandlers = handlers.get(type);
        
        if (typeHandlers == null) {
        	typeHandlers = new HashSet<HandlerInfo>();
            handlers.put(type, typeHandlers);
        }
        HandlerInfo info = new HandlerInfo();
        info.handler = new WeakReference<Object>(handler);
        info.method = method;
        // Add the listener
        typeHandlers.add(info);
	}

	public void publish(Object event) {
		Set<HandlerInfo> typeHandlers = handlers.get(event.getClass());
		if (typeHandlers==null) return;
		
		Collection<HandlerInfo> invalidHandlers = new LinkedList<HandlerInfo>();
		
		for(HandlerInfo info : typeHandlers) {
			boolean handled = false;
			Object handler = info.handler.get();
			if (handler!=null) {
				try {
					info.method.invoke(handler, event);
					handled = true;
				} catch (IllegalArgumentException e) {					
					e.printStackTrace();
				} catch (IllegalAccessException e) {						
					e.printStackTrace();
				} catch (InvocationTargetException e) {				
					e.printStackTrace();
				}
			}
			if (!handled) invalidHandlers.add(info);
		}
		for(HandlerInfo info : invalidHandlers) {
			typeHandlers.remove(info);
		}
		if (typeHandlers.size()==0) 
			handlers.remove(event.getClass());		
	}
}