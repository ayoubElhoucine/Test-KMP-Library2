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
            url: "https://github.com/ayoubElhoucine/Test-KMP-Library2/releases/download/1.0.3/shared.xcframework.zip",
            checksum: "340dea794567e21024f17751865f21e74d44353bc9dc3ded7cd9438175a5ba3f"
        ),
        .binaryTarget(
            name: "submodule",
            url: "https://github.com/ayoubElhoucine/Test-KMP-Library2/releases/download/1.0.3/submodule.xcframework.zip",
            checksum: "d2a65dcc26849a6c9a9545e3fe44a376e2d0c75118f4268748aba729be1a0891"
        )
    ]
)