"use strict";

import {paths} from "../gulp-configure";
import gulp from "gulp";
import debug from "gulp-debug";
import browsersync from "browser-sync";

gulp.task("thymeleaf", () => {
    return gulp.src(paths.thymeleaf.src)
        .pipe(gulp.dest(paths.thymeleaf.target))
        .pipe(debug({
            "title": "Html files"
        }))
        .pipe(browsersync.stream());
});