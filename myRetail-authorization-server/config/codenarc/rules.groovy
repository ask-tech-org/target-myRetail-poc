ruleset {
    // the master rule list: http://codenarc.sourceforge.net/StarterRuleSet-AllRulesByCategory.groovy.txt
    // configuring rules: http://codenarc.sourceforge.net/codenarc-configuring-rules.html
    
    description 'myRetail Codenarc RuleSet'

    ruleset('rulesets/basic.xml')
    ruleset('rulesets/braces.xml')
    ruleset('rulesets/concurrency.xml')
    ruleset('rulesets/convention.xml') {
        'FieldTypeRequired' { doNotApplyToClassNames = '*Test,*Spec' }
        'NoDef' { doNotApplyToClassNames = '*Test,*Spec' }
        'VariableTypeRequired' { doNotApplyToClassNames = '*Test,*Spec' }
        'MethodParameterTypeRequired' { doNotApplyToClassNames = '*Test,*Spec' }
        'MethodReturnTypeRequired' { doNotApplyToClassNames = '*Test,*Spec' }
        'TrailingComma' { enabled = false }
    }
    ruleset('rulesets/design.xml') {
        'PrivateFieldCouldBeFinal' { enabled = false }
    }
    ruleset('rulesets/dry.xml') {
        'DuplicateListLiteral' { doNotApplyToClassNames = '*Test,*Spec' }
        'DuplicateMapLiteral' { doNotApplyToClassNames = '*Test,*Spec' }
        'DuplicateNumberLiteral' { doNotApplyToClassNames = '*Test,*Spec' }
        'DuplicateStringLiteral' { doNotApplyToClassNames = '*Test,*Spec' }
    }
    ruleset('rulesets/exceptions.xml') {
        'ThrowException' { doNotApplyToClassNames = '*Test,*Spec' }
    }
    ruleset('rulesets/formatting.xml') {
        'TrailingWhitespace' { enabled = false }
        'FileEndsWithoutNewline' { enabled = false }
        'ClassJavadoc' { enabled = false }
        'LineLength' { enabled = false }
        'BlockEndsWithBlankLine' { enabled = false }
        'BlockStartsWithBlankLine' { enabled = false }
        'ConsecutiveBlankLines' { enabled = false }
        'SpaceAfterIf' { enabled = false }
        'SpaceAroundMapEntryColon' { enabled = false }
        'SpaceAfterCatch' { enabled = false }
        'SpaceAfterClosingBrace' { enabled = false }
    }
    ruleset('rulesets/generic.xml')
    ruleset('rulesets/groovyism.xml')
    ruleset('rulesets/imports.xml') {
        'NoWildcardImports' { enabled = false }
    }
    ruleset('rulesets/jdbc.xml')
    ruleset('rulesets/junit.xml') {
        'JUnitTestMethodWithoutAssert' { enabled = false }
    }
    ruleset('rulesets/logging.xml')
    ruleset('rulesets/naming.xml') {
        'MethodName' { doNotApplyToClassNames = '*Spec' }
        'FactoryMethodName' { enabled = false }
    }
    ruleset('rulesets/security.xml')
    ruleset('rulesets/serialization.xml')
    ruleset('rulesets/size.xml') {
        'ParameterCount' { enabled = false }
        'CrapMetric' { enabled = false }
    }
    ruleset('rulesets/unnecessary.xml') {
        'UnnecessaryReturnKeyword' { enabled = false }
        'UnnecessaryGString' { enabled = false }
        'UnnecessaryGetter' { enabled = false }
        'UnnecessarySetter' { enabled = false }
        'UnnecessaryObjectReferences' { doNotApplyToClassNames = '*Test,*Spec' }
    }
    ruleset('rulesets/unused.xml')
}