"use strict";

import { paths } from "../gulp-configure";
import gulp from "gulp";
import debug from "gulp-debug";
import browsersync from "browser-sync";

gulp.task("images", () => {
    return gulp.src(paths.images.src)
        .pipe(gulp.dest(paths.images.target))
        .pipe(debug({
            "title": "Images"
        }))
        .pipe(browsersync.stream());
});