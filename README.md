# **Cabrite** for automation web ui testing
use maven command to run a testng suite

`mvn clean test -DxmlFileName=testng_suite_fullname`

###testng suite parameter
`browser`:chrome(default), firefox, edge.
`url`:web site for tested, i.e. http://localhost:3000/.

### testng test parameter
`importFile`: It is not full path.
`expectationFile`: It is not full path.

#### properties
test.source=Z:/Quality/MeshViewer_TestData/(default, no need to set. Every test importFile and expectationFile are located at there.)



  

