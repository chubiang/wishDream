const gulp = require('gulp');
const eslint = require('gulp-eslint');

gulp.task('default', function () {
    gulp.watch(['**/*.{js,jsx}','!node_modules/**'], function() {
        gulp.src(['**/*.{js,jsx}','!node_modules/**'])
        .pipe(eslint())
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
    });
});