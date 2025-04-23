Meta:
@age

Scenario: 測試 JBehave
Given 給一個字 <str>
When 呼叫 API
Then 取得結果: <expected>

Examples:
|str|expected|
|11|Age is 11|
|15|Age is 15|