<h2>🎨 UI Core Layer — Development Branch (03-UI-Core)</h2>

<p>This branch focuses on establishing and evolving the <strong>UI foundation</strong> of the application architecture — including naming conventions, animation resources, colors, and reusable helpers for form and date/time input.</p>

<hr/>

<h3>📌 Purpose</h3>
<p>
  The <code>Development/03-UI-Core</code> branch acts as the <strong>base layer for UI styling, interaction, and structure</strong>. It consolidates essential components like:
</p>

<ul>
  <li>🔤 Naming conventions for consistency</li>
  <li>🎨 Shared color and font styles</li>
  <li>📅 Date and time input utilities</li>
  <li>🧪 Form validation helpers</li>
  <li>💫 Base animations and reusable UI interactions</li>
</ul>

<hr/>

<h3>✅ Summary of Key Changes</h3>

<h4>🧱 UI Architecture Setup (from Feature/3.2-Core-Architecture-Setup)</h4>
<ul>
  <li><strong>Moved</strong> all <code>Date/Time Pickers</code> to <code>date_time_helpers/</code></li>
  <li><strong>Moved</strong> <code>TextInputEditTextUtils.kt</code> to <code>form_helpers/</code></li>
  <li><strong>Moved</strong> <code>DateUtils.kt</code> to <code>formatters/</code></li>
  <li><strong>Relocated</strong> dialog layout to <code>res/layout/dialogs/</code></li>
  <li><strong>Added</strong> color palettes to <code>colors.xml</code></li>
  <li><strong>Added</strong> animation files for fade, slide, etc.</li>
</ul>

<h4>🧭 Naming Standards (from Feature/3.1-Naming-Standards)</h4>
<ul>
  <li>➕ Introduced <code>naming_convention.xml</code> and UI widget short name principles</li>
  <li>📁 Guidelines ensure naming consistency across XML, code, and resources</li>
  <li>📌 Examples:
    <ul>
      <li><code>btn_main_primary.xml</code> for primary button styles</li>
      <li><code>edt_username_input.xml</code> for input fields</li>
    </ul>
  </li>
</ul>

<hr/>

<h3>🧠 Why This Matters</h3>
<ul>
  <li>✅ <strong>Developer Productivity</strong> → Structured folders and consistent naming save time during collaboration</li>
  <li>🎯 <strong>Scalability</strong> → Easy to expand UI features and styling rules without clutter</li>
  <li>🎨 <strong>Design Consistency</strong> → Shared colors, fonts, and animation styles make your app feel polished</li>
</ul>

<hr/>

<h3>📁 Folder Overview</h3>

<table>
  <thead>
    <tr>
      <th>Category</th>
      <th>Folder</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>📅 Date/Time Helpers</td>
      <td><code>date_time_helpers/</code></td>
      <td>Pickers for date, time, and month-year dialogs</td>
    </tr>
    <tr>
      <td>🧪 Form Helpers</td>
      <td><code>form_helpers/</code></td>
      <td>Validation helpers like TextInputEditTextUtils</td>
    </tr>
    <tr>
      <td>📆 Formatters</td>
      <td><code>formatters/</code></td>
      <td>Date utilities and string formatting helpers</td>
    </tr>
    <tr>
      <td>🎨 Styling</td>
      <td><code>res/values/colors.xml</code></td>
      <td>Centralized color palette</td>
    </tr>
    <tr>
      <td>💫 Animations</td>
      <td><code>res/anim/</code></td>
      <td>Reusable animation XMLs for transitions</td>
    </tr>
    <tr>
      <td>📋 Guidelines</td>
      <td><code>res/xml/naming_convention*.xml</code></td>
      <td>Naming standards and UI principles</td>
    </tr>
  </tbody>
</table>

<hr/>

<h3>📅 Commits Summary</h3>

<ul>
  <li><strong>Mar 24, 2025</strong>: Refactored folder structure for UI helpers</li>
  <li><strong>Mar 24, 2025</strong>: Added animation XMLs</li>
  <li><strong>Mar 24, 2025</strong>: Introduced base color scheme</li>
  <li><strong>Mar 22, 2025</strong>: Added naming conventions and UI guidelines</li>
</ul>

<hr/>

<h3>🚀 When to Use This Branch</h3>

<p>Use this branch if you are:</p>

<ul>
  <li>🔧 Setting up UI components across multiple features</li>
  <li>🧪 Creating reusable form logic and validations</li>
  <li>🖌️ Applying base themes, fonts, or UI behaviors</li>
</ul>

<hr/>

<h3>🔗 Related Branches</h3>

<ul>
  <li><strong>Feature/3.1-Naming-Standards</strong> — UI naming rules and styling conventions</li>
  <li><strong>Feature/3.2-Core-Architecture-Setup</strong> — Base classes and shared utilities</li>
</ul>
