// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "KMP-SPM-Package",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "shared",
            targets: ["shared"]
        ),
        .library(
            name: "submodule",
            targets: ["submodule"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "shared",
            path: "./Library.xcframework"
        ),
        .binaryTarget(
            name: "submodule",
            path: "./Submodule.xcframework"
        )
    ]
)