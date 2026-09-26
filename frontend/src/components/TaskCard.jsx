import React from 'react';
import { Calendar, Edit3, Trash2, Clock, AlertTriangle } from 'lucide-react';
import { STATUS_CONFIG, PRIORITY_CONFIG, TASK_STATUS } from '../utils/constants';
import { formatDate, isOverdue } from '../utils/helpers';

export default function TaskCard({ task, onEdit, onDelete, onStatusChange }) {
  const statusInfo = STATUS_CONFIG[task.status] || STATUS_CONFIG[TASK_STATUS.TODO];
  const priorityInfo = PRIORITY_CONFIG[task.priority] || PRIORITY_CONFIG.MEDIUM;
  const overdue = isOverdue(task.dueDate, task.status);

  return (
    <div className="group relative bg-white border border-slate-200/80 hover:border-slate-300 rounded-2xl p-5 shadow-sm hover:shadow-md transition-all flex flex-col justify-between">
      <div>
        {/* Top: Priority & Status Badges */}
        <div className="flex items-center justify-between gap-2 mb-3">
          <span
            className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-semibold border ${priorityInfo.color}`}
          >
            {priorityInfo.label} Priority
          </span>

          <span
            className={`inline-flex items-center gap-1.5 px-2.5 py-0.5 rounded-full text-xs font-semibold border ${statusInfo.color}`}
          >
            <span className={`w-1.5 h-1.5 rounded-full ${statusInfo.dot}`}></span>
            {statusInfo.label}
          </span>
        </div>

        {/* Title */}
        <h3 className="font-semibold text-slate-900 text-base mb-1.5 leading-snug line-clamp-2">
          {task.title}
        </h3>

        {/* Description */}
        <p className="text-sm text-slate-500 mb-4 line-clamp-3 leading-relaxed whitespace-pre-wrap">
          {task.description || 'No description provided.'}
        </p>
      </div>

      <div>
        {/* Due Date & Overdue Indicator */}
        <div className="flex items-center justify-between pt-3 border-t border-slate-100 text-xs mb-3.5">
          <div className="flex items-center gap-1.5 text-slate-500">
            <Calendar className="w-3.5 h-3.5" />
            <span>{formatDate(task.dueDate)}</span>
          </div>

          {overdue && (
            <span className="flex items-center gap-1 text-rose-600 font-medium">
              <AlertTriangle className="w-3.5 h-3.5" /> Overdue
            </span>
          )}
        </div>

        {/* Footer actions: Status switcher + Edit + Delete */}
        <div className="flex items-center justify-between gap-2">
          <select
            value={task.status}
            onChange={(e) => onStatusChange(task.id, e.target.value)}
            className="text-xs font-medium rounded-lg border border-slate-200 bg-slate-50 py-1.5 px-2.5 text-slate-700 hover:bg-slate-100 focus:outline-none focus:ring-2 focus:ring-indigo-500 cursor-pointer transition-colors"
          >
            <option value="TODO">To Do</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="COMPLETED">Completed</option>
          </select>

          <div className="flex items-center space-x-1">
            <button
              onClick={() => onEdit(task)}
              title="Edit Task"
              className="p-1.5 text-slate-400 hover:text-indigo-600 hover:bg-indigo-50 rounded-lg transition-colors"
            >
              <Edit3 className="w-4 h-4" />
            </button>
            <button
              onClick={() => onDelete(task.id)}
              title="Delete Task"
              className="p-1.5 text-slate-400 hover:text-rose-600 hover:bg-rose-50 rounded-lg transition-colors"
            >
              <Trash2 className="w-4 h-4" />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
