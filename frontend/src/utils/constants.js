export const TASK_STATUS = {
  TODO: 'TODO',
  IN_PROGRESS: 'IN_PROGRESS',
  COMPLETED: 'COMPLETED',
};

export const TASK_PRIORITY = {
  LOW: 'LOW',
  MEDIUM: 'MEDIUM',
  HIGH: 'HIGH',
};

export const STATUS_CONFIG = {
  [TASK_STATUS.TODO]: {
    label: 'To Do',
    color: 'bg-amber-50 text-amber-700 border-amber-200',
    dot: 'bg-amber-500',
  },
  [TASK_STATUS.IN_PROGRESS]: {
    label: 'In Progress',
    color: 'bg-blue-50 text-blue-700 border-blue-200',
    dot: 'bg-blue-500',
  },
  [TASK_STATUS.COMPLETED]: {
    label: 'Completed',
    color: 'bg-emerald-50 text-emerald-700 border-emerald-200',
    dot: 'bg-emerald-500',
  },
};

export const PRIORITY_CONFIG = {
  [TASK_PRIORITY.LOW]: {
    label: 'Low',
    color: 'bg-slate-100 text-slate-700 border-slate-200',
    iconColor: 'text-slate-500',
  },
  [TASK_PRIORITY.MEDIUM]: {
    label: 'Medium',
    color: 'bg-orange-50 text-orange-700 border-orange-200',
    iconColor: 'text-orange-500',
  },
  [TASK_PRIORITY.HIGH]: {
    label: 'High',
    color: 'bg-rose-50 text-rose-700 border-rose-200',
    iconColor: 'text-rose-500',
  },
};
