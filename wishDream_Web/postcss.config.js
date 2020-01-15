module.exports = (ctx) => ({
    parser: ctx.parser ? 'sugarss' : false,
    map: ctx.env === 'production' ? ctx.map : false,
    plugins: {
        'postcss-plugin': {},
        'postcss-import': { index: ctx.file.dirname },
        'postcss-preset-env': {
            'browsers': 'last 2 versions'
        },
        'cssnano': ctx.env === 'production' ? {} : false
    }
})

// npx postcss *.css --use autoprefixer -d build/ <- 개발 끝나고 cmd입력
