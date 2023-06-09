import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {

    let appComponent = AppComponent()

    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController(
            appComponent: appComponent,
            onOpenBrowser: {
                let url = URL(string:$0)!
                UIApplication.shared.open(url, options: [:], completionHandler: nil)
            }
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}
