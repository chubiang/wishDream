const path = require('path');
const Fiber = require('fibers');

module.exports = {
    context: path.resolve(__dirname, 'app'),
    entry: {
        app: './app.js'
    },
    devtool: 'sourcemaps',
    output: {
        path: path.resolve(__dirname, 'src/main/webapp'),
        filenmae: 'js/[name].bundle.js'
    },
    mode: 'none',
    module: {
        rules: [
            { 
                test: /\.js?$/,
                exclude: /(node_modules)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [ '@babel/preset-env', '@babel/preset-react' ]
                    }
                }
             }, {
                 test: /\.scss$/,
                 use: [ { loader: 'style-loader' },
                        { loader: 'css-loader'   },
                        {
                            loader: 'sass-loader',
                            options: {
                                implementation: require("sass"),
                                fiber: Fiber
                            }
                        }
                     ] 
             }
        ]
    }
};