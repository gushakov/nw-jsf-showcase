/*
 * Based on source for log4j project
 * and http://www.javaworld.com/javaworld/jw-12-2004/jw-1220-toolbox.html?page=5
 */

package ch.unil.sflight.java.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;

public class SapLocationAppender extends AppenderSkeleton {

	@Override
	protected void append(LoggingEvent event) {

		// make sure that layout was declared
		Layout layout = getLayout();
		if (layout == null) {
			throw new RuntimeException("No layout set for "
					+ getClass().getCanonicalName());
		}

		// trace message based on the level of the event
		String message = layout.format(event);

		int level = event.getLevel().toInt();				
		Location loc = Location.getLocation(event.getLoggerName());
		
		if (level==Level.DEBUG.toInt()){
			loc.logT(Severity.DEBUG, message);
		}
		else if (level==Level.INFO.toInt()){
			loc.logT(Severity.INFO, message);
		}
		else if (level==Level.ERROR.toInt()){
			loc.logT(Severity.ERROR, message);
		}
		else {
			loc.logT(Severity.ALL, message);
		}
	}

	@Override
	public void close() {
		// nothing to do
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

}
