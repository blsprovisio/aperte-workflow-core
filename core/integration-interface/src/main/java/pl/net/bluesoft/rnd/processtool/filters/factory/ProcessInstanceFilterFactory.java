package pl.net.bluesoft.rnd.processtool.filters.factory;

import pl.net.bluesoft.rnd.processtool.model.ProcessInstanceFilter;
import pl.net.bluesoft.rnd.processtool.model.QueueType;
import pl.net.bluesoft.rnd.processtool.model.UserData;
import pl.net.bluesoft.rnd.processtool.model.nonpersistent.ProcessQueue;

import static pl.net.bluesoft.rnd.processtool.model.QueueType.*;

/**
 * Filter factory to encapsulte filter creation logic
 * @author Maciej Pawlak
 *
 */
public class ProcessInstanceFilterFactory 
{
	public ProcessInstanceFilter createMyTasksFilter(UserData user) {
		return getProcessInstanceFilter(user, "activity.created.assigned.tasks", MY_TASKS);
	}

	public ProcessInstanceFilter createMyTasksInProgress(UserData user) {
		return getProcessInstanceFilter(user, "activity.created.tasks", OWN_IN_PROGRESS);
	}

	/** Methods creates new filter which returns user closed tasks */
	public ProcessInstanceFilter createMyClosedTasksFilter(UserData user)
	{
		return getProcessInstanceFilter(user, "activity.created.closed.tasks", OWN_FINISHED);
	}

	/** Methods creates new filter which returns tasks created by given user and assigned to him */
	public ProcessInstanceFilter createSubstitutedMyTasksFilter(UserData substitutedUser)
	{
		return getProcessInstanceFilter(substitutedUser, "activity.subst.created.assigned.tasks", MY_TASKS);
	}

	/** Methods creates new filter which returns tasks created by given user, but done by others */
	public ProcessInstanceFilter createSubstitutedTasksInProgress(UserData substitutedUser)
	{
		return getProcessInstanceFilter(substitutedUser, "activity.subst.created.tasks", OWN_IN_PROGRESS);
	}

	/** Methods creates new filter which returns user closed tasks */
	public ProcessInstanceFilter createSubstitutedClosedTasksFilter(UserData substitutedUser)
	{
		return getProcessInstanceFilter(substitutedUser, "activity.subst.created.closed.tasks", OWN_FINISHED);
	}

	/** Methods creates new filter which returns user closed tasks */
	public ProcessInstanceFilter createOtherUserTaskForSubstitutedUser(UserData substitutedUser)
	{
		return getProcessInstanceFilter(substitutedUser, "activity.other.users.tasks", MY_TASKS);
	}
	
	private ProcessInstanceFilter getProcessInstanceFilter(UserData user, String name, QueueType... types)
	{
		ProcessInstanceFilter pif = new ProcessInstanceFilter();
		pif.setFilterOwner(user);
		pif.setName(name);
		
		for(QueueType queueType: types) {
			pif.addQueueType(queueType);
		}
		return pif;
	}
}
