# iDempiere Plugin Template

## Standard

- New callout
    * Name: CName
    * Package: com.ingeint.template.callout
    * Example: com.ingeint.template.callout.CStringFormat

- New process
    * Name: PName
    * Package: com.ingeint.template.process
    * Example: com.ingeint.template.process.PGenerateWithholding

- New form
    * Name: FName
    * Package: com.ingeint.template.form
    * Example: com.ingeint.template.form.FMultiPayment

- New event
    * Name: EName
    * Package: com.ingeint.template.event
    * Example: com.ingeint.template.event.EAfterCompleteInvoice

- New model (extends class X)
    * Name: MName
    * Package: com.ingeint.template.model
    * Example: com.ingeint.template.model.MTableExample

## Folder estructure

```
    com.ingeint.template
        |_.settings
        |   |_org.eclipse.core.resources.prefs
        |   |_org.eclipse.jdt.core.prefs
        |   |_org.eclipse.m2e.core.prefs
        |   |_org.eclipse.pde.core.prefs
        |_.classpath
        |_.project
        |_.gitignore
        |_build.properties
        |_LICENSE
        |_README.md
        |_pom.xml
        |_META-INF
        |   |_MANIFEST.MF
        |   |_2Pack_X.X.X.zip
        |_OSGI-INF
        |   |_CalloutFactory.xml
        |   |_EventFactory.xml
        |   |_FormFactory.xml
        |   |_ModelFactory.xml
        |   |_ProcessFactory.xml
        |_src
            |_com.ingeint.template
                |_base (plugin core)
                |   |_BundleInfo.java (gets plugin information dynamically)
                |   |_CustomCallout.java (IColumnCallout implementation)
                |   |_CustomCalloutFactory.java (IColumnCalloutFactory implementation)
                |   |_CustomEventFactory.java (AbstractEventHandler implementation)
                |   |_CustomEvent.java (for event implementation)
                |   |_CustomFormFactory.java (IFormFactory implementation)
                |   |_CustomForm.java (IFormController implementation)
                |   |_CustomModelFactory.java (IModelFactory implementation)
                |   |_CustomProcessFactory.java (IProcessFactory implementation)
                |   |_CustomProcess.java (SvrProcess implementation)
                |_component (plugin's components)
                |   |_CalloutFactory.java (register class callout)
                |   |_EventFactory.java (register class event handler)
                |   |_FormFactory.java (register class form)
                |   |_ProcessFactory.java (register class process)
                |   |_ModelFactory.java (register class model)
                |_util
                |   |_TimestampUtil.java
                |   |_SqlBuilder.java
                |   |_XmlTemplateBuilder.java
                |   |_KeyValueLogger.java
                |_callout (new callouts, extends CustomCallout)
                |_event (new events, extends CustomEvent)
                |_form (new forms, extends CustomForm)
                |_process (new processes, extends CustomProcess)
                |_model (autogenerated models)
```

## How it works

### Components

- New callout
    * Create callout in package `com.ingeint.template.callout`, extends from `CustomCallout`
    * Register callout in `com.ingeint.template.component.CalloutFactory`. Example:

```java
    protected void initialize() {
        registerCallout(MTableExample.Table_Name, MTableExample.COLUMNNAME_Text, CPrintPluginInfo.class);
    }
```

- New process
    * Create process in package `com.ingeint.template.process`, extends from `CustomProcess`
    * Register process in `com.ingeint.template.component.ProcessFactory`. Example:

```java
    protected void initialize() {
        registerProcess(PPrintPluginInfo.class);
    }
```

- New form
    * Create form in package `com.ingeint.template.form`, extends from `CustomForm`
    * Register form in `com.ingeint.template.component.FormFactory`. Example:

```java
    protected void initialize() {
        registerForm(FPrintPluginInfo.class);
    }
```

- New event
    * Create event in package `com.ingeint.template.event`, extends from `CustomEvent`
    * Register event in `com.ingeint.template.component.EventFactory`. Example:

```java
    protected void initialize() {
        registerEvent(IEventTopics.DOC_BEFORE_COMPLETE, MTableExample.Table_Name, EPrintPluginInfo.class);
    }
```

- New model (extends form class X)
    * Create model in package `com.ingeint.template.model`, extends class `X`. Example: `X_TL_TableExample -> MTableExample`
    * Register model in `com.ingeint.template.component.ModelFactory`. Example:

```java
    protected void initialize() {
        registerModel(MTableExample.Table_Name, MTableExample.class);
    }
```

### Adding a new library

Add the new dependency (`artifacItem`) to the [pom.xml](pom.xml) file in the `artifactItems` attribute, example:

```xml
    <artifactItems>
        <artifactItem>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>28.0-jre</version>
        </artifactItem>
    </artifactItems>
```

Then, add a new classpath entry in the [.classpath](.classpath) file, example:
```xml
    <classpathentry kind="lib" path="lib/guava.jar"/>
```

Verify you are including the folder `lib` in the [build.properties](build.properties) file, exaple:

```properties
bin.includes = .,\
               META-INF/,\
               OSGI-INF/,\
               lib/
```

Finally, add the new dependency in de [MANIFEST.MF](META-INF/MANIFEST.MF) file as a `Bundle-ClassPath` attribute, example:

```manifest
Bundle-ClassPath: .,
 lib/guava.jar
```

---

> Plugin skeleton generated by https://github.com/ingeint/idempiere-plugin-scaffold