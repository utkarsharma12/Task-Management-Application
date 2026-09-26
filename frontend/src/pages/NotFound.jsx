import React from 'react';
import { Link } from 'react-router-dom';
import { Home, ArrowLeft } from 'lucide-react';

export default function NotFound() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-slate-50 px-4">
      <div className="max-w-md w-full text-center space-y-6 bg-white p-10 rounded-3xl border border-slate-200/80 shadow-sm">
        <div className="text-7xl font-extrabold text-indigo-600 tracking-tight">404</div>
        <div className="space-y-2">
          <h2 className="text-xl font-bold text-slate-900">Page Not Found</h2>
          <p className="text-sm text-slate-500">
            The page you are looking for doesn't exist or has been moved.
          </p>
        </div>
        <div className="pt-2">
          <Link
            to="/dashboard"
            className="inline-flex items-center gap-2 px-5 py-2.5 rounded-xl bg-indigo-600 hover:bg-indigo-700 text-white font-semibold text-sm shadow-sm transition-all"
          >
            <Home className="w-4 h-4" />
            <span>Back to Dashboard</span>
          </Link>
        </div>
      </div>
    </div>
  );
}
