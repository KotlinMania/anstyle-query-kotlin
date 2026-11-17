import Testing
import AnstyleQuery

@Suite("AnstyleQuery Swift Export Tests")
struct AnstyleQueryExportTests {
    @Test("All query functions and Windows stub execute cleanly through Swift Export")
    func testEnvironmentQueries() {
        _ = clicolor()
        _ = clicolorForce()
        _ = noColor()
        _ = termSupportsColor()
        _ = termSupportsAnsiColor()
        _ = truecolor()
        _ = isCi()
        #expect(windows.enableAnsiColors() == nil)
    }
}
