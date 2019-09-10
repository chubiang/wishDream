module.exports = {
    "parser": "babel-eslint",
    "env": {
        "browser": true,
        "es6": true,
        "jquery": true,
        "mocha": true
    },
    "extends": ["airbnb","prettier"],
    "globals": {
        "Atomics": "readonly",
        "SharedArrayBuffer": "readonly"
    },
    "parserOptions": {
        "ecmaFeatures": {
            "jsx": true
        },
        "ecmaVersion": 2018,
        "sourceType": "module"
    },
    "plugins": [
        "react",
        "react-hooks"
    ],
    "rules": {
        "camelcase": "always",
        "vars-on-top": 0,
        "no-var": 2,
        "no-multi-assign": 0,
        "spaced-comment": 0,
        "no-underscore-dangle": 0,
        "func-names": 0,
        "no-return-assign": 0,
        "no-unused-vars": 0,
        "no-else-return": 1,
        "semi": ["never", {"beforeStatementContinuationChars": "any"}],
        "quotes": ["error", "single", { "avoidEscape": true, "allowTemplateLiterals": true }],
        "use-isnan": 1,
        "no-unreachable": 1,
        "space-unary-ops": 2,
        "valid-typeof": 2,
        "no-extra-semi": 2,
        "no-self-assign": 2,
        "no-await-in-loop": 2,
        "no-compare-neg-zero": 2,
        "no-self-compare": 2,
        "no-prototype-builtins": 2,
        "react/prefer-stateless-function": 0,
        "react/jsx-filename-extension": 0,
        "react/jsx-one-expression-per-line": 0,
        "react-hooks/rules-of-hooks": "error",
        "react-hooks/exhaustive-deps": "warn",
        "import/no-unresolved": "off"
    },
    "overrides": {
        "files": ["*-test.js"],
        "rules": {
            "no-unused-expressions": "off"
        }
    },
    "globals": {
        "jQuery": true,
        "$": true
    }
};