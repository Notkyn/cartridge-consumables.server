"use strict";

import {paths} from "../gulp-configure";
import gulp from "gulp";
import del from "del";

gulp.task("clean", () => {
    return del(paths.clean.static, paths.clean.target);
});