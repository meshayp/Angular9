# Differences in Angular 9 and 8, 7, 6, 5, 4, 2


### Angular Versioning
Angular version numbers indicate the level of changes that are introduced by the release. This article will help you understand the differences between these versions.

### What’s New In Angular 9:
1. Angular 9 Released October/November 2019
2. Added undecorated classes migration schematic in the core.
3. The formControlName also accepts a number in the form
4. Now allow selector-less directives as base classes in View Engine in the compiler.
5. Added support selector-less directive as base classes in Ivy and also make the Ivy compiler the default for ngc.
6. Convert all ngtsc diagnostics to ts.Diagnostics
7. Added bazel - support ts_library targets as entry-points for ng_package.
8. Added core - add dynamic queries schematic.
9. Added core - Mark TestBed.get as deprecated.
10. Added ivy- expose window.ng.getDebugNode helper and also support ng-add in localize package.
11. Added ivy- i18n – add syntax support for $localize metadata block.
12. Added ivy - i18n – reorganizes entry-points for better reuse.
13. Enable logging on TypeScriptHost for language-service
14. Provide diagnostic for invalid templateUrls for language-service
15. Provide diagnostics for invalid styleUrls for language-service
16. Update rxjs peerDependencies minimum requirement
17. Support ng-add in localize package.
18. Allow retrieving synchronized analyzed NgModules

### What’s New In Angular 8:
1. Angular 8 Released April/May 2019
2. Ivy: Next-gen Renderer for Angular Framework
    * Shipment of pre-compiled code
    * Compilation without the need of Metadata.json
    * Meta programming
3. Added Web Workers
4. Added Lazy Loading
5. Improvement in ng-upgrade
6. Added Support for Node 10
7. Improvements on CLI workflow
8. Upgrading Angular Material
9. Added TypeScript 3.4 support
10. Added Differential Modern JavaScript Loading
11. Improved Web Worker Bundling
12. Added a Navigation Type Available during Navigation in the Router
13. Added pathParamsOrQueryParamsChange mode for runGuardsAndResolvers in the Router
14. Allow passing state to routerLink Directives in the Router
15. Allow passing state to NavigationExtras in the Router
16. Restore whole object when navigating back to a page managed by Angular Router
17. Added support for SASS
18. Resolve generated Sass/Less files to .css inputs
19. Added Angular Router Backward Compatibility
20. Enhanced Web Worker Bundling

### What’s New In Angular 7:
1. Angular 7 Released on October 2018
2. This is a major release and expanding to the entire platform including-
    * Core framework
    * Angular Material
    * CLI
3. Added a new interface - UrlSegment[] to CanLoad interface
4. Added a new interface -  DoBootstrap interface
5. Angular 7 added a new compiler - Compatibility Compiler (ngcc)
6. Introduce a new Pipe called - KeyValuePipe
7. Angular 7 now supporting to TypeScript 3.1
8. Added Virtual Scrolling features
9. Added Drag & Drop features
10. Added Better Error Handling
11. Added a new elements features - enable Shadow DOM v1 and slots
12. Added a new router features - warn if navigation triggered outside Angular zone
13. Added a new mapping for ngfactory and ngsummary files to their module names in AOT summary resolver.
14. Added a new "original" placeholder value on extracted XMB
15. Added a new ability to recover from malformed URLs
16. Added a new compiler support dot (.) in import statements and also avoid a crash in ngc-wrapped
17. Update compiler to flatten nested template fns
18. Added Native Script
19. Added Bundle Budget

### What’s New In Angular 6:
1. Angular 6 Released on April 2018
2. Added Angular Element
3. Added Service worker   
4. Added Internationalization (i18n)      
5. Added Bazel Compiler  
6. Added New commons - ng-add / ng-update     
7. Added Model change events - ngModelChange
8. Added TypeScript 2.7 support 
9. Improved decorator error messages
10. <ng-template> updated to <template>     

### What’s New Angular 5?
Included Key Features -
1. Include Representation of Placeholders to xliff and xmb in the compiler
2. Include an Options Arg to Abstract Controls in the forms controls 
3. Include add default updateOn values for groups and arrays to form controls
4. Include updateOn blur option to form controls
5. Include updateOn submit option to form controls
6. Include an Events Tracking Activation of Individual Routes
7. Include NgTemplateOutlet API as stable in the common controls
8. Create StaticInjector which does not depend on Reflect polyfill
9. Include [@.disabled] attribute to disable animation children in the animations
10. Make AOT the default
11. Watch mode
12. Type checking in templates
13. More flexible metadata
14. Remove *.ngfactory.ts files
15. Better error messages
16. Smooth upgrades
17. Tree-Shakeable components
18. Hybrid Upgrade Application

### What’s New Angular 4?
1. Smaller & Faster Apps
2. View Engine Size Reduce
3. Animation Package
4. NgIf and ngFor Improvement
5. Template
6. NgIf with Else
7. Use of AS keyword
8. Pipes
9. HTTP Request Simplified
10. Apps Testing Simplified
11. Introduce Meta Tags
12. Added some Forms Validators Attributes
13. Added Compare Select Options
14. Enhancement in Router
15. Added Optional Parameter
16. Improvement Internationalization