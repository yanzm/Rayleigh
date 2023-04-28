import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {

    let authRepository = AppComponent().authRepository

    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController(
            authRepository: authRepository,
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
