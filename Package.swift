// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "KMP-SPM-Package",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "MainModule",
            targets: ["MainModule"]
        ),
        .library(
            name: "Submodule",
            targets: ["Submodule"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "MainModule",
            path: "./Library.xcframework"
        ),
        .binaryTarget(
            name: "Submodule",
            path: "./Submodule.xcframework"
        )
    ]
)