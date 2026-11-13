#if canImport(Testing)
import Testing
import AnstyleQuery

@Suite("AnstyleQuery Swift Export Tests")
struct AnstyleQueryExportTests {
    @Test("AnstyleQuery swift module imported cleanly")
    func testSwiftModuleLoads() throws {
        #expect(Bool(true), "AnstyleQuery swift module imported cleanly")
    }
}
#elseif canImport(XCTest)
import XCTest
import AnstyleQuery

final class AnstyleQueryExportTests: XCTestCase {
    func testSwiftModuleLoads() throws {
        XCTAssertTrue(true, "AnstyleQuery swift module imported cleanly")
    }
}
#endif
