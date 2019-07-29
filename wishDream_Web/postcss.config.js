//if(process.env.NODE_ENV === 'production') {
    module.exports = {
        plugins: [
            require('autoprefixer'),
            require('cssnano'),
        ]
    }
//}
// npx postcss *.css --use autoprefixer -d build/ <- 개발 끝나고 cmd입력
