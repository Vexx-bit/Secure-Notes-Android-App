# Resource architecture
- `res/layout/`: activity, fragment, and ListView row XML.
- `res/values/strings.xml`: all displayed text.
- `res/values/colors.xml` and `values-night/colors.xml`: day/night palette.
- `res/values/styles.xml`: reusable widgets and typography.
- `res/values/themes.xml` and `values-night/themes.xml`: app theme.
- `res/values/dimens.xml`: reusable spacing, sizes, and corners.
- `res/drawable/`: vector icons and shape resources.
- `res/menu/`: bottom-navigation items.
- `res/anim/`: fragment fade transitions.

This reflects the class notes: resources remain outside Kotlin, use lowercase underscore names, and are referenced through generated `R` identifiers.
