BDD Demo
---

- description:
  - Record BDD implementation methods
- dependencies (Cucumber is used in goat-core):
  - JBehave (mvn test)
  - Concordion (mvn test)
  - Serenity with JBehave (mvn clean verify)
  - Serenity with Cucumber (mvn clean verify)

### Introduction

---

- Serenity with JBehave: 
  - Because JBehave defined their own process class, "serenity-jbehave" needs to extend it. which's also limited Serenity version.
  - To use: 
    1. Create your "Story" class & "Steps" Class.
    2. Add TestStoryConfig on your Story class to define what steps and story you'd use.
    3. You can also inject Spring bean in your Step Class by constructor (Qualifier is supported).