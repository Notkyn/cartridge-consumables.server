"use strict";

import { paths } from "../gulp-configure";
import gulp from "gulp";
import debug from "gulp-debug";
import browsersync from "browser-sync";

gulp.task("fonts", () => {
    return gulp.src(paths.fonts.src)
        .pipe(gulp.dest(paths.fonts.target))
        .pipe(debug({
            "title": "Fonts"
        }))
        .pipe(browsersync.stream());
});