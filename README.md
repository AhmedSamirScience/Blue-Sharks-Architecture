## 🧱 Core Architecture Setup — Phase 3.2

This feature branch focuses on establishing a clean, scalable foundation for the Android application architecture by introducing base components and organizing utilities.

---

### ✅ Key Highlights

#### 🎯 Core Components
- **BaseActivity** & **BaseFragment**
  - Shared lifecycle-aware setup
  - Abstracted common UI logic
  - Improved testability and reusability

#### 🔁 UX & Interaction Helpers
- **BackPressedHandler**: Central manager for custom back navigation
- **ViewModelStateHandler**: UI state binding from ViewModel (`Loading`, `Success`, `Error`, `Empty`)
- **DelayedActionHandler**: Throttle or debounce UI events like search inputs
- **SoftKeyboardUtils**: Hide/show the soft keyboard programmatically

#### 🧩 Utility Packages
- `date_time_helpers/` — Date picker, time picker, month-year dialogs
- `form_helpers/` — Input validation tools (e.g., TextInputEditTextUtils)
- `formatters/` — Utility functions for date/time formatting (e.g., DateUtils)

#### 🧾 UI Enhancements
- **AnimationHelper.kt**: Easily apply fade, scale, or custom animations
- **SpinnerAdapter** with custom dropdown layout
- Moved `dialog_month_year_picker.xml` to `res/layout/dialogs/` for better resource organization

#### 🎨 Style & Theme
- Base color resources added for consistent design system
  - Example: `colorPrimary`, `colorSecondary`, `colorGrayDark`, etc.

---

### 📁 Folder Structure Changes

| Old Path | New Path |
|----------|----------|
| `TextInputEditTextUtils.kt` | `form_helpers/TextInputEditTextUtils.kt` |
| `DateUtils.kt` | `formatters/DateUtils.kt` |
| `dialog_month_year_picker.xml` | `res/layout/dialogs/dialog_month_year_picker.xml` |
| `*Date/Time Pickers` | `date_time_helpers/` |

---

### 🔍 Benefits

- ✅ **Better Modularity** — Clear separation of concerns
- ✅ **Improved Scalability** — Well-named folders help future teams navigate faster
- ✅ **Developer Productivity** — Fewer bugs and faster development due to reusable helpers
- ✅ **Cleaner Git History** — Meaningful commits, each scoped to a particular area

---

### 📅 Commits Summary

| Date | Commit |
|------|--------|
| Mar 22, 2025 | `feat: add core activity/fragment base classes and utility components` |
| Mar 22, 2025 | `refactor(ui): reorganize date/time picker helpers, input validation, and utils into structured folders` |
| Mar 22, 2025 | `Added Animation files` |
| Mar 22, 2025 | `Added Base Colors` |

---

### 🧠 When to Use This Branch

Use this branch if:
- You are working on **foundation-layer features**
- You need **base UI logic**, **navigation handling**, or **lifecycle-aware utilities**
- You are building reusable infrastructure before jumping into features

---
