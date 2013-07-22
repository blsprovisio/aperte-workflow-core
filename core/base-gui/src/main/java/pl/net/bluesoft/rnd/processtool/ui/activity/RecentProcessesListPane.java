package pl.net.bluesoft.rnd.processtool.ui.activity;

import java.util.Calendar;
import java.util.List;

import pl.net.bluesoft.rnd.processtool.model.BpmTask;
import pl.net.bluesoft.rnd.processtool.model.ProcessInstanceFilter;
import pl.net.bluesoft.rnd.processtool.model.QueueType;

/**
 * @author tlipski@bluesoft.net.pl
 */
public class RecentProcessesListPane extends MyProcessesListPane {

	private Calendar minDate;

	public RecentProcessesListPane(ActivityMainPane activityMainPane, String title) {
		super(activityMainPane, title);
	}

    public void setMinDate(Calendar minDate) {
        this.minDate = minDate;
    }

    @Override
	public List<BpmTask> getBpmTasks() {
        return getBpmSession().findRecentTasks(minDate.getTime(), offset, limit);
	}

	@Override
	protected ProcessInstanceFilter getDefaultFilter() {
		ProcessInstanceFilter tfi = new ProcessInstanceFilter();
		tfi.setFilterOwner(getBpmSession().getUser());
//		tfi.addOwner(getBpmSession().getUser());
		tfi.setUpdatedAfter(minDate.getTime());
		tfi.addQueueType(QueueType.MY_TASKS);
		return tfi;
	}
}
