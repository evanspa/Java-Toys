Just an FYI so I don't forget, there is a file in the folder: 
SpringClientConfigTesting/XMLInstances_for_testing/:
'expectedRequest_template.xml.'  The reason why the name of the file starts
with 'expected' is because the Spring-WS-test client-side testing mechanism
leverages mocking.  Mocking is used to validate that the request XML message
produced by our client code matches the contents of
'expectedRequest_template.xml' (the file contains FreeMarker tags - hence the
'_template' in the name).

For some reason, when using Spring-WS-test to unit test our Spring web
service configuration, mocking is not leveraged; there is no notion of
an "expected" response.  Therefore all we have in the 
SpringWsConfigTesting/XMLInstances_for_testing/ folder are simulated request
XML strings.

